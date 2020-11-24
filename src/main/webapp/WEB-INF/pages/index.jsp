<%@ page contentType="text/html;charset=UTF-8" %>
<div class="content">
    <h1>FourThings+</h1>
    <p class="lead">
        FourThings+ er en fed app til at holde styr på ens indkøbslister.
    </p>

    <svg xmlns="http://www.w3.org/2000/svg"
         height="500"
         width="500"
         viewBox="0 0 37 28">
        <style>
            .flag-farve1 {
                fill: red;
            }

            .flag-farve2 {
                fill: white;
            }
        </style>
        <rect class="flag-farve1" x="0" y="0" width="12" height="12"/>
        <rect class="flag-farve1" x="16" y="0" width="21" height="12"/>
        <rect class="flag-farve1" x="0" y="16" width="12" height="12"/>
        <rect class="flag-farve1" x="16" y="16" width="21" height="12"/>
        <rect class="flag-farve2" x="12" y="0" width="4" height="28"/>
        <rect class="flag-farve2" x="0" y="12" width="37" height="4"/>
    </svg>

    ${chessboard}
</div>
