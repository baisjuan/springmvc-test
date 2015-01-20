<!DOCTYPE html>

<html ng-app="spring.mvc.test">

<head>

    <title>Spring MVC Test</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link rel="stylesheet" href="style/bootstrap.min.css"/>
    <link rel="stylesheet" href="style/custom.css"/>

</head>

<body>

    <div id="mainTitle">
        <h1><span class="label label-primary">Spring MVC Test</span></h1>
    </div>

    <div ng-controller="TabsController">

        <tabset>

            <tab heading="Foods">
                <div class="tabContent">
                    Name:
                    <input id="food_name_add" type="text">
                    Calories:
                    <input id="calories" type="number" style="width:75px;"><br>
                    <input class="type_food" type="checkbox" id="breakfast"/>Breakfast
                    <input class="type_food" type="checkbox" id="lunch"/>Lunch
                    <input class="type_food" type="checkbox" id="supper"/>Supper
                    <input class="type_food" type="checkbox" id="dinner"/>Dinner<br><br>
                    <input id="add_new_food" type="button" value="Add food"/>

                    <div id="foodsTable" class="panel panel-default" style="margin-top: 30px; display: none;">
                        <!-- Default panel contents -->
                        <div class="panel-heading">Foods</div>
                        <div class="panel-body">
                            <p>Full list of foods retrieved from the database</p>
                        </div>

                        <!-- Table -->
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>Name</th>
                                <th>Calories</th>
                                <th>Breakfast</th>
                                <th>Lunch</th>
                                <th>Supper</th>
                                <th>Dinner</th>
                            </tr>
                            </thead>
                            <tbody id="foodsTableBody">

                            </tbody>
                        </table>
                    </div>

                </div>
            </tab>

            <tab heading="Diets">
                <div class="tabContent">
                    Calories:
                    <input id="diet_calories" type="number" style="width:75px;"><br><br>
                    Breakfast(%):
                    <input id="breakfast_percentage" type="number" style="width:40px;" placeholder="15">
                    Lunch(%):
                    <input id="lunch_percentage" type="number" style="width:40px;" placeholder="35">
                    Supper(%):
                    <input id="supper_percentage" type="number" style="width:40px;" placeholder="15">
                    Dinner(%):
                    <input id="dinner_percentage" type="number" style="width:40px;" placeholder="35"><br><br>
                    <input id="suggest_diets" type="button" value="Suggest diets" onclick=""/> <br> <br>
                    Name:
                    <input id="diet_name" type="text"> <br><br>
                    <input id="add_diet" type="button" value="Add diet"/>
                    <input id="delete_diet" type="button" value="Delete diet"/><br><br><br>
                    <input id="list_diets" type="button" value="Show diets"/>
                </div>
            </tab>

            <tab heading="Solr">
                <div class="tabContent">
                    <a href="http://localhost:8983/solr/update/?stream.body=<delete><query>*:*</query></delete>&commit=true">Delete
                        all documents from Solr hitting the server directly</a><br><br>
                    <input id="delete_documents" type="button" value="Delete documents from Solr"/><br>
                    <input id="add_document" type="button" value="Add hardcoded document to Solr"/><br>
                    <input id="add_documents" type="button" value="Add many hardcoded documents to Solr"/><br>
                    <input id="exe_search" type="button" value="Execute search"/><br>
                    <input id="commit" type="button" value="Commit"/>
                </div>
            </tab>

        </tabset>

    </div>

    <script type="text/javascript" src="lib/jquery.js"></script>
    <script type="text/javascript" src="lib/food.js"></script>
    <script type="text/javascript" src="lib/main.js"></script>
    <script type="text/javascript" src="lib/diet.js"></script>
    <script type="text/javascript" src="lib/solr.js"></script>
    <script type="text/javascript" src="lib/angular.min.js"></script>
    <script type="text/javascript" src="lib/ui-bootstrap.min.js"></script>
    <script type="text/javascript" src="lib/angular.controllers.js"></script>

    <style></style>

</body>

</html>