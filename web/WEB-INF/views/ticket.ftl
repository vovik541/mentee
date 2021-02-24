<#import "templates/common.ftl" as c>
<@c.page "Ticket">
<table>
    <tr>
        <td>| Id: ${ticket.id} | </td>
        <td>Event Id: ${ticket.eventId} | </td>
        <td>User Id: ${ticket.userId} | </td>
        <td>Ticket Category: ${ticket.ticketCategory} | </td>
        <td>Place: ${ticket.place} |</td>
    </tr>
</table>
    <a href="/mentee/ticket/actions">back</a>
</@c.page>