let mongoose = require('mongoose');

let schema = mongoose.Schema({
    name : { type : String, required : true },
    category : { type : String, required : true },
    place : { type : String, required : true },
    location : { type : String, required : true },
    date : { type : String, required : true },
    stars : { type : Number, required : true },
    recommend : { type : Boolean, required : true }
}, { collection : "print" });

let model = mongoose.model("print", schema);

module.exports = model;