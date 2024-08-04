package platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.model.CodeResponse;
import platform.repository.CodeResponseRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CodeService {
    @Autowired
    private CodeResponseRepository codeResponseRepository;

    // Add a new code snippet to the repository
    public CodeResponse addCode(String code, int time, int views) {
        CodeResponse codeResponse = new CodeResponse(code, time, views);
        return codeResponseRepository.save(codeResponse);
    }

    // Retrieve a code snippet by its UUID
    public CodeResponse getCode(String uuid) {
        CodeResponse codeResponse = codeResponseRepository.findByUuid(uuid).orElse(null);
        if(codeResponse != null) {
            // Check if time restriction has expired
            if(codeResponse.hadTimeRestriction() && !codeResponse.hasTimeRestriction()) {
                codeResponseRepository.delete(codeResponse);
                return null;
            }
            // Check if views restriction has expired
            if(codeResponse.hadViewsRestriction() && !codeResponse.hasViewsRestriction()) {
                codeResponseRepository.delete(codeResponse);
                return null;
            }
        }
        if(codeResponse != null && !codeResponse.isExpired()) {
            // Decrement views if there's a view restriction
            if (codeResponse.hasViewsRestriction()) {
                codeResponse.decrementViews();
                codeResponseRepository.save(codeResponse);
            }
            return codeResponse;
        }
        // Delete expired snippets
        if (codeResponse != null && codeResponse.isExpired()) {
            codeResponseRepository.delete(codeResponse);
        }
        return null;
    }

    // Get the latest unrestricted and non-expired code snippets
    public List<CodeResponse> getLatestCodes() {
        List<CodeResponse> list = codeResponseRepository.findAll();
        Collections.reverse(list);
        return list.stream()
                .filter(code -> !code.isRestricted() && !code.isExpired())
                .limit(10)
                .collect(Collectors.toList());
    }
}
