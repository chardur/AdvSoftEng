const { deploy } = require('sftp-sync-deploy');

let config = {
  host: '18.223.204.244',
  port: 22,
  username: 'ec2-user',
  privateKey: __dirname + '/cs3750.pem',
  localDir: __dirname + '/dist/shmoozed',
  remoteDir: '/var/www/html/'
};

let options = {
  dryRun: false,
  exclude: [
    'node_modules',
    'src/**/*.spec.ts'
  ],
  excludeMode: 'remove',
  forceUpload: false
};

deploy(config, options).then(() => {
  console.log('success!');
  process.exit(0);
}).catch(err => {
  console.error('error! ', err);
  process.exit(1);
})
