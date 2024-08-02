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

    public CodeResponse addCode(String code) {
        CodeResponse codeResponse = new CodeResponse(code);
        return codeResponseRepository.save(codeResponse);
    }

    public CodeResponse getCode(Long id) {
        return codeResponseRepository.findById(id).orElse(null);
    }

    public List<CodeResponse> getLatestCodes() {
        List<CodeResponse> list = codeResponseRepository.findAll();
        Collections.reverse(list);
        return list.stream().limit(10).collect(Collectors.toList());
    }
}
