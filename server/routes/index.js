let router = require('express').Router();

let printRouter = require('./print/');

router.use('/', printRouter);
module.exports = router;