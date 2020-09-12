/**
 * Activity.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    name: {
      type: 'string',
      required: true,
      minLength: 3,
      maxLength: 256
    },
    type: {
      type: 'string',
      required: true,
      minLength: 3,
      maxLength: 256,
      isIn: ['Tour','Film','Performance', 'Exhibition']
    },
    location: {
      type: 'string',
      required: true,
      minLength: 3,
      maxLength: 1000
    },
    initialDate: {
      type: 'ref',
      required: true,
      columnName: 'initial_date',
      columnType: 'datetime'
    },
    finalDate: {
      type: 'ref',
      required: true,
      columnName: 'final_date',
      columnType: 'datetime'
    },
    duration: {
      type: 'string',
      required: true,
      minLength: 3,
      maxLength: 10,
      regex: /([0-1]?[0-9]|2[0-3]):[0-5][0-9]/
    },
    pmName: {
      type: 'string',
      required: true,
      columnName: 'pm_name',
      minLength: 3,
      maxLength: 256
    },
    pmPhoneNumber: {
      type: 'string',
      required: true,
      columnName: 'pm_phone_number',
      minLength: 3,
      maxLength: 15,
      regex: /\(?\+[0-9]{1,3}\)? ?-?[0-9]{1,3} ?-?[0-9]{3,5} ?-?[0-9]{4}( ?-?[0-9]{3})? ?(\w{1,10}\s?\d{1,6})?/
    },
    price: {
      type: 'number',
      required: true,
      columnType: 'FLOAT'
    },
    description: {
      type: 'string',
      required: true,
      minLength: 3,
      maxLength: 1000
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

    schedules: {
      collection: 'schedule',
      via: 'activity'
    },
    activityArtworks: {
      collection: 'activityartwork',
      via: 'activity'
    }

  }
};

