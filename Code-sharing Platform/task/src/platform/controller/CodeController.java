package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import platform.model.CodeResponse;
import platform.service.CodeService;

@RestController
public class CodeController {

    @Autowired
    private CodeService codeService;

    @GetMapping(value = "/code", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> getCodeHtml() {
        String html = "<html><head><title>Code</title></head><body><pre>" + codeService.getCodeResponse().getCode() + "</pre></body></html>";
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(html);
    }

    @GetMapping(value = "/api/code", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CodeResponse> getCodeJson() {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(codeService.getCodeResponse());
    }
}
