<#import "templates/common.ftl" as c>
<@c.page "Tickets">
<table>
    <#list tickets as ticket>
        <tr>
            <td>| Id: ${ticket.id} | </td>
            <td>Event Id: ${ticket.eventId} | </td>
            <td>User Id: ${ticket.userId} | </td>
            <td>Ticket Category: ${ticket.ticketCategory} | </td>
            <td>Place: ${ticket.place} |</td>
        </tr>
    </#list>
</table>
    <a href="/mentee/ticket/actions">back</a>
</@c.page>