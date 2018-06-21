<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Index Page</title>
        <!-- Bootstrap core CSS -->
        <!--<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">--> 
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">

    </head>
    <body>
        <div class="container">
            <h1 class="text-center">Vending Machine</h1>
            <div class="row">
                <c:forEach var="currentCandy" items="${items}">

                    <div class="card border-secondary mb-3 col-3" id="${currentCandy.itemId}" style="max-width: 18rem;"> 
                        <div class="card-text">${currentCandy.itemId}</div>
                        <div class="card-body text-dark">
                            <h5 class="card-title text-center">${currentCandy.candyName}</h5>
                            <p class="card-text text-center">$ ${currentCandy.price}</p> 
                            <p class="card-text text-center">${currentCandy.inventoryAmount}</p> 
                            <form action="selectItem" method="GET">
                                <input type="hidden" name="candyId" value="${currentCandy.itemId}"/>
                                <button type="submit" class="btn btn-default">Choose item</button>
                            </form>
                        </div>
                    </div>

                </c:forEach>
            </div>


            <div class="row">

                <div class="col-3" style="max-width: 18rem;">
                    <div class="card-body text-dark">

                        <label for="add-coins" class="control-label"><h4>Total $ in</h4></label>
                        <input type="string" class="form-control" id="add-coins" value="${currentMoney}" readonly required/>

                        <form class="form-horizontal" role="form" id="add-one-dollar" method="GET" action="addMoney">
                            <div class="row">
                                <input type="hidden" name="amount" value="1.0">
                                <button type="submit" id="add-dollar" class="btn btn-dark">Add Dollar</button>
                            </div>
                        </form>

                        <form class="form-horizontal" role="form" id="add-one-quarter" method="GET" action="addMoney">
                            <div class="row">
                                <input type="hidden" name="amount" value="0.25">
                                <button type="submit" id="add-quarter" class="btn btn-dark">Add Quarter</button>
                            </div>
                        </form>

                        <form class="form-horizontal" role="form" id="add-one-dime" method="GET" action="addMoney">
                            <div class="row">
                                <input type="hidden" name="amount" value="0.10">
                                <button type="submit" id="add-dime" class="btn btn-dark">Add Dime</button>
                            </div>
                        </form>

                        <form class="form-horizontal" role="form" id="add-one-nickel" method="GET" action="addMoney">
                            <div class="row">
                                <input type="hidden" name="amount" value="0.05">
                                <button type="submit" id="add-nickel" class="btn btn-dark">Add Nickel</button>
                            </div>
                        </form>

                    </div>
                </div>


                <div class="col-3" style="max-width: 18rem;">
                    <div class="card-body text-dark">

                        <form class="form-horizontal" role="form" id="add-money">

                            <div class="form-group">
                                <label for="messages" class="control-label">
                                    <h4>Messages</h4>
                                </label>
                                <input type="text" class="form-control form-control-lg" id="messages" value="${message}${deficitMessage}" readonly required/>
                            </div>
                            <div class="form-group">
                                <div class="row">
                                    <label for="item-display" class="control-label">
                                        <h4>Item:</h4>
                                    </label>
                                    <input type="number" class="form-control" id="item-display" value="${candyId}" readonly required/>
                                </div>
                            </div>
                        </form>

                        <form action="dispatchItem" method="GET">
                            <input type="hidden" name="currentMoney" value="${currentMoney}"/>
                            <input type="hidden" name="candyId" value="${candyId}"/>
                            <button type="submit" class="btn btn-primary">Make Purchase</button>
                        </form>

                    </div>
                </div>



                <div class="col-3" style=" max-width: 18rem;">
                    <div class="card-body text-dark">
                        <form role="form" id="return-change">
                            <div class="form-group">
                                <label for="give-change" class="control-label">
                                    <h4>Change</h4>
                                </label>
                                <input type="text" class="form-control form-control-lg" id="give-change" value="${change}" readonly required/>
                            </div>
                        </form>
                        <form action="cancelTransaction" method="GET">
                            <div class="form-group">
                                <div class="row">
                                    <input type="hidden" name="currentMoney" value="${currentMoney}"/>
                                    <button type="submit" id="cancel-transaction" class="btn btn-danger"> Change return </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>





        </div>
        <!-- Placed at the end of the document so the pages load faster -->
<!--        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>-->

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>

    </body>
</html>

