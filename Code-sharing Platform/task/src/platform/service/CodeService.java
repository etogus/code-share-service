package platform.service;

import org.springframework.stereotype.Service;
import platform.model.CodeResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CodeService {
    private List<CodeResponse> codes = new ArrayList<>();

    public CodeResponse addCode(String code) {
        long id = codes.size() + 1;
        CodeResponse codeResponse = new CodeResponse(id, code);
        codes.add(codeResponse);
        return codeResponse;
    }

    public CodeResponse getCode(long id) {
        return codes.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<CodeResponse> getLatestCodes() {
        List<CodeResponse> latestCodes = new ArrayList<>(codes);
        Collections.reverse(latestCodes);
        return latestCodes.subList(0, Math.min(10, latestCodes.size()));
    }
}
