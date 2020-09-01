/**
 * Purchase.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    attendanceDate: {
      type: 'ref',
      required: true,
      columnName: 'attendance_date',
      columnType: 'datetime'
    },
    purchaseTime: {
      type: 'ref',
      required: true,
      columnName: 'purchase_time',
      columnType: 'datetime'
    },
    quantity: {
      type: 'number',
      required: true,
      isInteger: true
    },
    total: {
      type: 'number',
      required: true,
      columnType: 'FLOAT'
    },
    status:{
      type: 'boolean',
      defaultsTo: true,
    },

    user: {
      model: 'user',
      required: true
    },
    schedule: {
      model: 'schedule',
      required: true
    }

    
  },

};

