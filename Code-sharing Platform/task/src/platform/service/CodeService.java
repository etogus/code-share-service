package platform.service;

import org.springframework.stereotype.Service;
import platform.model.CodeResponse;

@Service
public class CodeService {
    private CodeResponse codeResponse;

    public CodeService() {
        String code = "public static void main(String[] args) {\n    SpringApplication.run(CodeSharingApplication.class, args);\n}";
        this.codeResponse = new CodeResponse(code);
    }

    public CodeResponse getCodeResponse() {
        return codeResponse;
    }

    public void setCodeResponse(String code) {
        if(!code.equals(this.codeResponse.getCode())) {
            this.codeResponse = new CodeResponse(code);
        }
    }
}
