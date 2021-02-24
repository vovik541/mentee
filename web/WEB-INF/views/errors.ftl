<#import "templates/common.ftl" as c>

<@c.page "Exception">
    failed at url: ${url}
    <p>

    <span>Exception: ${exception.message}</span>
</@c.page>