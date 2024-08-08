package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import platform.model.CodeResponse;
import platform.service.CodeService;

import java.util.List;
import java.util.Map;

@Controller
public class CodeController {

    @Autowired
    private CodeService codeService;

    // Handle GET request for viewing a specific code snippet (HTML)
    @GetMapping(value = "/code/{uuid}")
    public String getCodeHtml(@PathVariable String uuid, Model model) {
        CodeResponse codeResponse = codeService.getCode(uuid);
        if(codeResponse == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Code snippet not found");
        }
        model.addAttribute("codeResponse", codeResponse);
        return "code";
    }

    // Handle GET request for viewing a specific code snippet (JSON)
    @GetMapping(value = "/api/code/{uuid}")
    public ResponseEntity<CodeResponse> getCodeJson(@PathVariable String uuid) {
        CodeResponse codeResponse = codeService.getCode(uuid);
        if(codeResponse == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .body(codeResponse);
    }

    // Handle POST request for adding a new code snippet
    @PostMapping(value = "/api/code/new")
    public ResponseEntity<Map<String, String>> addCode(@RequestBody Map<String, Object> jsonObj) {
        String code = (String) jsonObj.get("code");
        int time = (int) jsonObj.getOrDefault("time", 0);
        int views = (int) jsonObj.getOrDefault("views", 0);
        CodeResponse codeResponse = codeService.addCode(code, time, views);
        return ResponseEntity.ok(Map.of("id", codeResponse.getUuid()));
    }

    // Handle GET request for the page to add a new code snippet
    @GetMapping("/code/new")
    public String getNewCodePage() {
        return "new";
    }

    // Handle GET request for latest code snippets (HTML)
    @GetMapping("/code/latest")
    public String getLatestCodeHtml(Model model) {
        model.addAttribute("codes", codeService.getLatestCodes());
        return "latest";
    }

    // Handle GET request for latest code snippets (JSON)
    @GetMapping("/api/code/latest")
    @ResponseBody
    public ResponseEntity<List<CodeResponse>> getLatestCodeJson() {
        return ResponseEntity.ok(codeService.getLatestCodes());
    }
}
