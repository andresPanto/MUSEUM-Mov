/**
 * Artwork.js
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
    year: {
      type: 'number',
      required: true,
      isInteger: true
    },
    type: {
      type: 'string',
      required: true,
      minLength: 3,
      maxLength: 256
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

    activityArtworks: {
      collection: 'activityartwork',
      via: 'artwork'
    },
    artworkAuthors: {
      collection: 'artworkauthor',
      via: 'artwork'
    }

  },

};

