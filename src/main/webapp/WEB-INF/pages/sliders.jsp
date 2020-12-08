<%--
  Created by IntelliJ IDEA.
  User: ckl
  Date: 04/12/2020
  Time: 10.21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="content">
    <c:if test="${carport != null}">
        <div>
            ${carport.drawing}
        </div>
    </c:if>
    <form method="post">
        <div class="form-row">
            <div class="form-group col-md-6">
                <label id="carport-width-label" for="carport_width">Bredde:
                    <output id="show_carport_width">${carport.width}</output>
                </label>
                <input type="range" value="${carport.width}" min="200" max="400" step="30"
                       class="form-control-range" id="carport_width"
                       name="carport_width"
                       oninput="show_carport_width.value = carport_width.value"/>
            </div>
            <div id="carport-height-label" class="form-group col-md-6">
                <label for="carport_height">Højde:
                    <output id="show_carport_height">${carport.height}</output>
                </label>
                <input type="range" min="200" value="${carport.height}" max="400" step="30" class="form-control-range"
                       id="carport_height"
                       name="carport_height"
                       oninput="show_carport_height.value = carport_height.value"
                       aria-valuenow="${carport.height}"
                />
            </div>
        </div>
        <div>
            <input class="btn btn-secondary" type="submit" value="refresh">
            <input class="btn btn-primary" type="submit" value="bestill">
        </div>
    </form>
</div>
