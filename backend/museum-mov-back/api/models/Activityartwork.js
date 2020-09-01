/**
 * Activityartwork.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  tableName: 'activity_artwork',

  attributes: {

    activity: {
      model: 'activity',
      required: true
    },
    artwork: {
      model: 'artwork',
      required: true
    }

  },

};

