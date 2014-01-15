var MyApp = MyApp || {};

(function () {

    var People = Backbone.Collection.extend({
        // Reference to this collections model
        model:MyApp.personModel,
        url:'/people',
        // parse response from fetch request
        parse:function (response) {
            return response.data;
        }
    });

    // create our global collection of people
    MyApp.people = new People();
})();