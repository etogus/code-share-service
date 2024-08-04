<html>
<head>
    <title>Create</title>
    <!-- Include syntax highlighting library -->
    <link rel="stylesheet"
           href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
    <!-- Textarea for entering new code -->
    <textarea id="code_snippet">// write your code here</textarea>
    <!-- Input for time restriction -->
    <input id="time_restriction" type="text" value="0"/>
    <!-- Input for views restriction -->
    <input id="views_restriction" type="text" value="0"/>
    <!-- Submit button -->
    <button id="send_snippet" type="submit" onclick="send()">Submit</button>

    <script>
    // Function to send the new code snippet to the server
    function send() {
        let object = {
            "code": document.getElementById("code_snippet").value,
            "time": parseInt(document.getElementById("time_restriction").value) || 0,
            "views": parseInt(document.getElementById("views_restriction").value) || 0
        };

        let json = JSON.stringify(object);

        let xhr = new XMLHttpRequest();
        xhr.open("POST", '/api/code/new', false)
        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        xhr.send(json);

        if (xhr.status == 200) {
          alert("Success!");
        }
    }
    </script>
</body>
</html>