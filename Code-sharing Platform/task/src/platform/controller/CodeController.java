package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import platform.model.CodeResponse;
import platform.service.CodeService;

import java.util.List;
import java.util.Map;

@Controller
public class CodeController {

    @Autowired
    private CodeService codeService;

    @GetMapping(value = "/code/{id}")
    public String getCodeHtml(@PathVariable long id, Model model) {
        CodeResponse codeResponse = codeService.getCode(id);
        model.addAttribute("codeResponse", codeResponse);
        return "code";
    }

    @GetMapping(value = "/api/code/{id}")
    public ResponseEntity<CodeResponse> getCodeJson(@PathVariable long id) {
        CodeResponse codeResponse = codeService.getCode(id);
        return ResponseEntity.ok()
                .body(codeResponse);
    }

    @PostMapping(value = "/api/code/new")
    public ResponseEntity<Map<String, String>> addCode(@RequestBody Map<String, String> jsonObj) {
        CodeResponse codeResponse = codeService.addCode(jsonObj.get("code"));
        return ResponseEntity.ok(Map.of("id", String.valueOf(codeResponse.getId())));
    }

    @GetMapping("/code/new")
    public String getNewCodePage() {
        return "new";
    }

    @GetMapping("/api/code/latest")
    @ResponseBody
    public ResponseEntity<List<CodeResponse>> getLatestCodeJson() {
        return ResponseEntity.ok(codeService.getLatestCodes());
    }

    @GetMapping("/code/latest")
    public String getLatestCodeHtml(Model model) {
        model.addAttribute("codes", codeService.getLatestCodes());
        return "latest";
    }
}
