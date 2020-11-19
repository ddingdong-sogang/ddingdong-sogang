const express = require('express');

const app = express();

app.listen(4000);

app.get('/', (req, res) => res.sendStatus(200));