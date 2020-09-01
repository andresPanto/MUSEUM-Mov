/**
 * Artworkauthor.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  tableName: 'artwork_author',

  attributes: {

    artwork: {
      model: 'artwork',
      required: true
    },
    author: {
      model: 'author',
      required: true
    }

  },

};

