/**
 * Author.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {
  

  attributes: {

    fullName: {
      type: 'string',
      required: true,
      columnName: 'full_name',
      minLength: 3,
      maxLength: 256
    },
    country: {
      type: 'string',
      required: true,
      minLength: 3,
      maxLength: 128
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

    artworkAuthors: {
      collection: 'artworkauthor',
      via: 'author'
    }
    
  },

};

