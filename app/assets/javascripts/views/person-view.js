var MyApp = MyApp || {};

(function ($) {

    MyApp.personView = Backbone.View.extend({
        tagName:'tr',
        template:_.template($('#personTemplate').html()),
        events:{
            'click .remove-btn':'destroy',
            'dblclick td':'edit',
            'blur .surname':'blur',
            'keypress .surname':'updateOnEnter'
        },
        initialize:function () {
            _.bindAll(this, 'render');

            this.listenTo(this.model, 'change', this.change);
            this.listenTo(this.model, 'destroy', this.remove);
        },
        change:function () {
            this.render();
        },
        // render template with this.model and return this
        render:function () {
            this.$el.html(this.template(this.model.toJSON()));

            // get input elements after rendering
            this.$firstname = this.$('.firstname');
            this.$surname = this.$('.surname');

            return this;
        },
        edit:function (e) {
            this.$el.addClass('active');
            this.$firstname.focus();
        },
        updateOnEnter:function (event) {
            if (event.which === ENTER_KEY) {
                this.updatePerson();
            }
        },
        updatePerson:function () {
            var firstname = this.$firstname.val().trim();
            var surname = this.$surname.val().trim();

            // TODO save only if there is no change
            if (firstname) {
                // TODO error handling on update
                this.model.save({ firstname:firstname, surname:surname });
            } else {
                // delete if firstname is blank
                this.destroy();
            }
        },
        blur:function (event) {

            // We don't want to handle blur events from an item that is no
            // longer being edited. Relying on the CSS class here has the
            // benefit of us not having to maintain state in the DOM and the
            // JavaScript logic.
            if (!this.$el.hasClass('active')) {
                return;
            }
            this.updatePerson();
            this.$el.removeClass('active');
        },
        destroy:function () {
            // TODO error handling on deletion
            this.model.destroy();
        }
    });

})(jQuery);
