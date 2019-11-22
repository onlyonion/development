var os = require('os');
var fs = require('fs');
var path = require('path');

var isWin = /^win/i.test(os.platform());
var configFile = path.resolve(os.homedir(), '.nbconfig');

var configs = { nbmLastTimer: 1574390342218,
    nbmLastVersion: '2.0.18',
    nbmLastPublish: 1532665054847 };

console.log(isWin, configFile, JSON.stringify(configs, null, 4))

fs.writeFileSync(configFile, JSON.stringify(configs, null, 4));
