var MyApp = MyApp || {};

(function ($) {

    MyApp.addView = Backbone.View.extend({
        el:'div#addPersonView',
        events:{
            'keypress #surname':'createOnEnter',
            'click button#newPerson':'createPerson',
            'keypress #firstname':'clearAlerts',
            'blur #firstname':'clearAlerts'
        },
        template:_.template($('#addPersonTemplate').html()),
        initialize:function () {
            this.$list = $("#peopleList>tbody");

            _.bindAll(this, 'render');
            this.render();

            this.clearInputs();

            // add person to view, when add event is fired on people collection
            this.listenTo(MyApp.people, 'add', this.addPerson);
            // add all person in people collection when reset event is fired
            this.listenTo(MyApp.people, 'reset', this.addAll);

            // fetch with reset to add data to collection
            // Suppresses 'add' events with {reset: true} and prevents the view
            // from being re-rendered for every model. Only renders when the 'reset'
            // event is triggered at the end of the fetch.
            MyApp.people.fetch({reset:true});
        },
        // render addPersonTemplate and return this
        render:function (alertMessage) {
            this.$el.html(this.template({message:alertMessage}));

            this.$firstname = this.$('#firstname');
            this.$surname = this.$('#surname');
            return this;
        },
        // persist data using people collection .create()
        createPerson:function () {
            var self = this;
            var newPerson = this.newPerson();
            if (newPerson) {
                MyApp.people.create(newPerson, {
                    success:function () {
                        self.clearInputs();
                    },
                    error:function () {

                    }
                });
            }
        },
        clearInputs:function () {
            this.$firstname.val('');
            this.$surname.val('');

            this.$firstname.focus();
        },
        createOnEnter:function (event) {
            if (event.which === ENTER_KEY) {
                this.createPerson();
            }
        },
        newPerson:function () {
            var trimmedFirstName = this.$firstname.val().trim();
            var trimmedLastName = this.$surname.val().trim();
            if (trimmedFirstName || trimmedLastName) {
                return {
                    firstname:trimmedFirstName,
                    surname:trimmedLastName
                };
            }
            this.showAlert("First name and last name cannot be blank, please enter either one of them");
            return false;
        },
        showAlert:function (alertMessage) {
            this.render(alertMessage);
            this.clearInputs();
        },
        clearAlerts:function () {
            $('.alert').alert('close');
        },
        // adds person to list view
        addPerson:function (person) {
            var view = new MyApp.personView({ model:person});
            this.$list.append(view.render().el);
            return false;
        },
        // Add all items in the people collection at once to view.
        addAll:function () {
            this.$list.html('');
            MyApp.people.each(this.addPerson, this);
        }
    });

})(jQuery);