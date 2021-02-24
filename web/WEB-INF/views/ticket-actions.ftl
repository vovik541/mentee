<#import "templates/common.ftl" as c>
<@c.page "Ticket Actions">
    <form action="book" method="post">
        <input value="Book ticket:" disabled>
        <input name="userId" type="text" required pattern="\d+$" placeholder="User Id"/>
        <input name="eventId" type="text" required pattern="\d+$" placeholder="Event Id"/>
        <input name="place" type="text" required pattern="\d+$" placeholder="Place"/>
        <input name="ticketCategoryName" type="text" placeholder="Ticket Category Name"/>
        <input type="submit" value="Book"/>
    </form>

    <form action="getBookedTicketsByUser">
        <input value="Get booked by user:" disabled>
        <input name="userId" type="text" required pattern="\d+$" placeholder="User Id"/>
        <input name="pageNum" type="text" required pattern="\d+$" placeholder="Page Num"/>
        <input name="pageSize" type="text" required pattern="\d+$" placeholder="Page Size"/>
        <input type="submit" value="Get Booked By User"/>
    </form>

    <form action="getBookedTicketsByEvent">
        <input value="Get booked by event:" disabled>
        <input name="eventId" type="text" required pattern="\d+$" placeholder="Event Id"/>
        <input name="pageNum" type="text" required pattern="\d+$" placeholder="Page Num"/>
        <input name="pageSize" type="text" required pattern="\d+$" placeholder="Page Size"/>
        <input type="submit" value="Get Booked By Event"/>
    </form>

    <form action="cancelTicket" method="post">
        <input value="Cancel ticket:" disabled>
        <input name="ticketId" type="text" required pattern="\d+$" placeholder="Ticket Id"/>
        <input type="submit" value="Cancel"/>
    </form>
</@c.page>
