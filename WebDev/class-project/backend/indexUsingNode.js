var http = require('http');
var port = 5000;

//Method that helps to create a server and the method passed in the arg is called by node
var server = http.createServer(function (req, res) {
  const path = req ? req.url : null;
  const reqMethod = req.method;

  console.log(`Req for ${path} received, with method ${reqMethod}`);
  if (req && path == "/") {
    res.writeHead(200, {'content-type' : 'text/plain'});
    res.write("Hello world");
    return;
  }
  if (req && path == "/products" && reqMethod === "GET") {

    res.writeHead(200, {'content-type' : 'application/json'});
    res.end(JSON.stringify([{
      "id" : 101,
      "name" : "Product 1",
      "category" : "top-seller",
      "price" : 100
    }]));
    return;
  }
  
    res.writeHead(404, {'content-type' : 'text/plain'});
    res.write("Path not found!");
  

  // if (req) {
  //   res.writeHead(200, {'content-type' : 'text/plain'}),
  //   res.end("Hello world!");
  // }
})
server.listen(port, "localhost", () => {
  console.log(`Server is running on http://localhost:${port}/`)
});

