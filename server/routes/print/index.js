let router = require('express').Router();

/**
 * 전체 조회
 */
router.route('/print').get(function (req, res) {
    let printModel = req.app.get('db').print;
    printModel.find({}, function (err, docs) {
        if (err) {
            console.log(err);
            res.status(400).end();
        } else {
            res.status(200).json(docs);
        }
    })
});


/**
 * 카테고리별 조회
 */
router.route('/print/:category').get(function (req, res) {
    let printModel = req.app.get('db').print;
    printModel.find({
        "category": req.params.category
    }, function (err, docs) {
        if (err) {
            console.log(err);
            res.status(400).end();
        } else {
            res.status(200).json(docs);
        }
    })
});

/**
 * 카테고리별 조회 -> 정렬
 */
router.route('/print/:category/:sortby').get(function (req, res) {
    let printModel = req.app.get('db').print;
    if (req.params.sortby !== "stars" && req.params.sortby !== "date") {
        res.status(400).end();
    } else {
        let sortBy = req.params.sortby;
        
        let sort = {};
        switch(sortBy){
            case "stars" : 
                sort = { "stars" : -1 };
                break;
            case "date" : 
                sort = { "date" : -1 };
                break
        }
        printModel.find({
            "category": req.params.category
        }, {
            _id: false,
            __v: false
        }, function (err, docs) {
            if (err) {
                console.log(err);
                res.status(400).end();
            } else {
                res.status(200).json(docs);
            }
        }).sort(sort);
    }
});



router.route('/print').post(function (req, res) {
    let printModel = req.app.get('db').print;

    new printModel(req.body).save(function (err) {
        if (err) {
            console.log(err);
            res.status(400).end();
        } else res.status(201).end();
    });
});

module.exports = router;