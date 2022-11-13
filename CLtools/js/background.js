chrome.contextMenus.create({
	title: "CLtools",
	onclick: function(){
        chrome.tabs.create({
            url: '../html/index.html'
        })
    }
});
chrome.contextMenus.create({
	title: 'Bing搜索：%s',
	contexts: ['selection'],
	onclick: function(params) {
		chrome.tabs.create({url: 'https://cn.bing.com/search?q=' + encodeURI(params.selectionText)});
	}
});
chrome.contextMenus.create({
	title: 'CSDN搜索：%s',
	contexts: ['selection'],
	onclick: function(params) {
		chrome.tabs.create({url: 'https://so.csdn.net/so/search?q=' + encodeURI(params.selectionText)});
	}
});
chrome.contextMenus.create({
	title: 'StackoverFlow搜索：%s',
	contexts: ['selection'],
	onclick: function(params) {
		chrome.tabs.create({url: 'http://stackoverflow.com/search?q=' + encodeURI(params.selectionText)});
	}
});
