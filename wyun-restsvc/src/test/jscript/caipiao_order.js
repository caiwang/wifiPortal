//phantomjs code to log in to 163.com
//based on the code from this Stackoverflow answer:
//http://stackoverflow.com/questions/9246438/how-to-submit-a-form-using-phantomjs
//I'm injecting jQuery so this assumes you have jquery in your project directory

var page = new WebPage(), testindex = 0, loadInProgress = false;
var stopPhantom = false;
var login_page_loaded = 0;
var fs = require("fs");

if (window._phantom) {
	  // Patch since PhantomJS does not implement click() on HTMLElement. In some 
	  // cases we need to execute the native click on an element. However, jQuery's 
	  // $.fn.click() does not dispatch to the native function on <a> elements, so we
	  // can't use it in our implementations: $el[0].click() to correctly dispatch.
	console.log('define click function: ');
	  if (!HTMLElement.prototype.click) {
	    HTMLElement.prototype.click = function() {
	      var ev = document.createEvent('MouseEvent');
	      ev.initMouseEvent(
	          'click',
	          /*bubble*/true, /*cancelable*/true,
	          window, null,
	          0, 0, 0, 0, /*coordinates*/
	          false, false, false, false, /*modifier keys*/
	          0/*button=left*/, null
	      );
	      this.dispatchEvent(ev);
	    };
	  }
	}

// configure page
page.settings.loadImages = true;

page.onError = function(msg, trace) {
    var msgStack = ['ERROR: ' + msg];
    if (trace && trace.length) {
        msgStack.push('TRACE:');
        trace.forEach(function(t) {
            msgStack.push(' -> ' + t.file + ': ' + t.line + (t.function ? ' (in function "' + t.function + '")' : ''));
        });
    }
    // uncomment to log into the console
    console.error(msgStack.join('\n'));
};

page.onLoadFinished = function(status) {
	var url = page.url;
	loadInProgress = false;
	console.log("Status:  " + status);
	console.log("Loaded:  " + url);
	
	var file = fs.open("output" + testindex + ".html", "w");
    file.write(page.content);
	file.close();
	
	page.render("output" + testindex + ".png");

};

page.onConsoleMessage = function(msg) {
	console.log(msg);
};

page.onLoadStarted = function() {
	loadInProgress = true;
	console.log("load started");
};



var steps = [
		function() {
			console.log("Load Login Page");
			page.settings.userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_2) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.6 Safari/537.11";
			page.open("http://caipiao.163.com/my/order.html");
		},
		function() {
			console.log("Enter Credentials");
	       
		    page.evaluate(function() {
			    document.querySelector('input[id="useremail"]').value = '13701272752@163.com';
			    document.querySelector('input[id="password"]').value = '123ZAQqaz';
				//$('#password').val('123ZAQqaz');
				console.log(document.title);
			});
			   
		}, 
		function() {
			console.log('login');
			page.evaluate(function() {
				console.log(document.title + 'click submit button now.');
				//$('#submitBtn').click();
				document.querySelector('input[id="submitBtn"]').click();
			});
			loadInProgress = true;
			
		},
		
		function(){
			//open the caipiao order form, assume that the login is successful
			var submit_url = "http://caipiao.163.com/order/ssq/#from=leftnav";
			
			page.onLoadFinished = function(status) {
				var url = page.url;
				loadInProgress = false;
				console.log("Status:  " + status);
				console.log("Loaded:  " + url);
				
				var file = fs.open("caipiao_order" + ".html", "w");
			    file.write(page.content);
				file.close();
				
				page.render("caipiao_order" + ".png");
				//stopPhantom = true;
				

			};
			
			page.open(submit_url);
			
		},
		//function to 
		function(){
			console.log("select red/blue balls randomly.");
			
			console.log("Loaded jQuery!");
			var injected = page.injectJs("jquery-2.1.3.min.js");
			console.log("jquery was injected successfully: " + injected);
			page.evaluate(function() {
				
				$jq = window.jQuery;
				$jq.noConflict();
				    
				var s_red = document.querySelector(".radom_redbtn"); //$(".radom_redbtn");
				var s_blue = document.querySelector(".radom_bluebtn");
				//var rect =  $('.radom_redbtn')[0].getBoundingClientRect();
				//page.sendEvent('click', rect.left + rect.width / 2, rect.top + rect.height / 2);
				
				//rect =  $('.radom_bluebtn')[0].getBoundingClientRect();
				//page.sendEvent('click', rect.left + rect.width / 2, rect.top + rect.height / 2);
				console.log("red: " + $jq('.radom_redbtn'));
				
				//eventFire(s_red, 'click');
				//eventFire(s_blue, 'click');
				console.log("select red balls:");
				$jq('.radom_redbtn').click();
				//$jq(s_red).click();
				console.log("select blue ball");
				$jq('.radom_bluebtn').click();
				//$jq(s_blue).click();
				
				//s_blue.click();
				$jq('.betbtn').click();
			});
			
			var file = fs.open("caipiao_order1" + ".html", "w");
		    file.write(page.content);
			file.close();
			
			page.render("caipiao_order" + ".png");
			stopPhantom = true;
		}]

//main function here.
interval = setInterval(function() {
	if (!loadInProgress && typeof steps[testindex] == "function") {
		console.log("step " + (testindex + 1));
		steps[testindex]();
		// page.render("images/step" + (testindex + 1) + ".png");
		testindex++;
	}
	if (stopPhantom) {
		console.log("test complete!");
		phantom.exit();
	}else{
		console.log("still in testing ...");
	}
}, 5000);




