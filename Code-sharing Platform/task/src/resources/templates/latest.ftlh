<html>
<head>
    <title>Latest</title>
</head>
<body>
    <h1>Latest</h1>
    <#list codes as code>
        <span id="load_date">${code.date}</span>
        <pre id="code_snippet">${code.code}</pre>
        <br>
    </#list>
</body>
</html>