let express = require('express');
let bodyParser = require('body-parser');
let sessionParser = require('express-session');
let morgan = require('morgan');
let database = require('./database');

let app = express();
let router = require('./routes');
app.set('port', 8080);

app.use(morgan('dev'));

app.use(bodyParser.urlencoded({
    extended: false
}));

app.use(sessionParser({
    key: 'sessionKey',
    secret: 'oi1uojooisd!@#*(@!#&(',
    resave: false
}));

app.use(bodyParser.json());
app.use('/', router);

app.route('/foods').get(function (req, res) {
    let response = [{
            "name": "카라멜 프라푸치노",
            "category": 7,
            "place": "스타벅스",
            "location": "강남역 4번출구 앞",
            "date": "2017-07-29",
            "stars": 3,
            "recommend": false
        },
        {
            "name": "치즈 떡볶이",
            "category": 6,
            "place": "죠스떡볶이",
            "location": "은행동",
            "date": "2017-07-29",
            "stars": 5,
            "recommend": true
        }
    ];
    res.status(200).json(response);
})
app.listen(app.get('port'), function () {
    console.log("Started :: " + app.get('port'));
    database.connect(app);
});