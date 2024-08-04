<html>
<head>
    <title>Code</title>
    <!-- Include syntax highlighting library -->
    <link rel="stylesheet"
           href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>

    <!-- Display the date when the code was loaded -->
    <span id="load_date">${codeResponse.date}</span>

    <!-- Display views restriction if applicable -->
    <#if codeResponse.hadViewsRestriction()>
        <span id="views_restriction">${codeResponse.views} more views allowed</span>
    </#if>

    <!-- Display time restriction if applicable -->
    <#if codeResponse.hasTimeRestriction()>
        <span id="time_restriction">The code will be available for ${codeResponse.time} seconds</span>
    </#if>

    <!-- Display the code snippet -->
    <pre id="code_snippet"><code>${codeResponse.code}</code></pre>
</body>
</html>