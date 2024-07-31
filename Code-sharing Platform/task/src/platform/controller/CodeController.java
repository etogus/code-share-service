package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import platform.model.CodeResponse;
import platform.service.CodeService;

import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
public class CodeController {

    @Autowired
    private CodeService codeService;

    @GetMapping(value = "/code", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> getCodeHtml() {
        String html = "<html>\n" +
                    "<head>\n" +
                    "    <title>Code</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <span id=\"load_date\">" + codeService.getCodeResponse().getDate() + "</span>\n" +
                    "    <pre id=\"code_snippet\">\n" + codeService.getCodeResponse().getCode() + "</pre>\n" +
                    "</body>\n" +
                    "</html>";
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

    @PostMapping(value = "/api/code/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> addCode(@RequestBody Map<String, String> payload) {
        codeService.setCodeResponse(payload.get("code"));
        return ResponseEntity.ok(Map.of());
    }

    @GetMapping(value = "/code/new", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> getNewCodePage() {
        String html = """
                <html>
                <head>
                    <title>Create</title>
                </head>
                <body>
                    <textarea id="code_snippet"></textarea>
                    <button id="send_snippet" type="submit" onclick="send()">Submit</button>
                    <script>
                    function send() {
                        let object = {
                            "code": document.getElementById("code_snippet").value
                        };
                       \s
                        let json = JSON.stringify(object);
                       \s
                        let xhr = new XMLHttpRequest();
                        xhr.open("POST", '/api/code/new', false)
                        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
                        xhr.send(json);
                       \s
                        if (xhr.status == 200) {
                          alert("Success!");
                        }
                    }
                    </script>
                </body>
                </html>""";
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(html);
    }
}
