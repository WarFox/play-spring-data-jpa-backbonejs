var MyApp = MyApp || {};

(function () {

    MyApp.personModel = Backbone.Model.extend({
        // set default values as blank string
        defaults:{
            firstname:"",
            surname:""
        },
        idAttribute:'id',
        urlRoot:'/people',
        // parse response from save function to get our data,
        // because our server sends response in custom format
        parse:function (response) {
            if (_.isObject(response.data)) {
                return response.data;
            } else {
                return response;
            }
        }
    });

})();
