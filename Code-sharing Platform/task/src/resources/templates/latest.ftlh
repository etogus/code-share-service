<html>
<head>
    <title>Latest</title>
    <!-- Include syntax highlighting library -->
    <link rel="stylesheet"
           href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
    <h1>Latest</h1>
    <!-- Iterate through the latest code snippets -->
    <#list codes as code>
        <!-- Display the date for each snippet -->
        <span id="load_date">${code.date}</span>
        <!-- Display the code for each snippet -->
        <pre id="code_snippet"><code>${code.code}</code></pre>
        <br>
    </#list>
</body>
</html>