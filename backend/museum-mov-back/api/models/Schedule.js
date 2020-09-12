/**
 * Schedule.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    schedule: {
      type: 'string',
      required: true,
      minLength: 3,
      maxLength: 11,
      regex: /([0-1]?[0-9]|2[0-3]):[0-5][0-9]-([0-1]?[0-9]|2[0-3]):[0-5][0-9]/
    },
    capacity: {
      type: 'number',
      required: true,
      isInteger: true
    },
    status:{
      type: 'boolean',
      defaultsTo: true,
    },

    purchases: {
      collection: 'purchase',
      via: 'schedule'

    },
    activity: {
      model: 'activity',
      required: true
    }

  },

};

