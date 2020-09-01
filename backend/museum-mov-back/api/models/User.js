/**
 * User.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    username: {
      type: 'string',
      required: true,
      minLength: 3,
      maxLength: 256
    },
    password: {
      type: 'string',
      regex: /^(?=\w*\d)(?=\w*[A-Z])(?=\w*[a-z])\S{8,16}$/
    },
    email: {
      type: 'string',
      isEmail: true
    },
    fullName: {
      type: 'string',
      required: true,
      columnName: 'full_name',
      minLength: 3,
      maxLength: 256
    },
    phoneNumber:{
      type: 'string',
      required: true,
      columnName: 'phone_number',
      minLength: 3,
      maxLength: 15,
      regex: /\(?\+[0-9]{1,3}\)? ?-?[0-9]{1,3} ?-?[0-9]{3,5} ?-?[0-9]{4}( ?-?[0-9]{3})? ?(\w{1,10}\s?\d{1,6})?/
    },
    imagePath: {
      type: 'string',
      required: true,
      columnName: 'img_path',
      minLength: 3,
      maxLength: 256
    },
    status:{
      type: 'boolean',
      defaultsTo: true,
    },

    purchases: {
      collection: 'purchase',
      via: 'user'
    }

  },

};

