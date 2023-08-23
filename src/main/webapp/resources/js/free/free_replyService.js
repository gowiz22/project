var replyService = {

	add : function(reply, callback, error) {
		$.ajax({
			type : 'post',
			url : `${ctxPath}/freeReplies/new`,
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result){
				if(callback) callback(result)
			},
			error : function(xhr, status, er){
				if(error) error(er)
			}
		})
	},
	
	getList : function(param, callback, error) {
		let bno = param.bno
		let page = param.page || 1;
		
		$.ajax({
			type : 'get', 
			url : `${ctxPath}/freeReplies/pages/${page}/${bno}`, 
			success : function(freeReplyPageDTO){
				if(callback) callback(freeReplyPageDTO.replyCount, freeReplyPageDTO.list);
			}, 
			error : function(xhr, status, er){
				if(error) error(er);
			}
		})
	},
	
	update : function(reply, callback, error) {
		$.ajax({
			type : 'put', 
			url : `${ctxPath}/freeReplies/${reply.rno}`, 
			data : JSON.stringify(reply), 
			contentType : "application/json; charset=utf-8", 
			success : function(result){
				if(callback) callback(result);
			}, 
			error : function(xhr, status, er){
				if(error) error(er);
			}
		});
	},
	
	remove : function(rno, callback, error) {
		$.ajax({
			type : 'delete', 
			url : `${ctxPath}/freeReplies/${rno}`, 
			success : function(result){
				if(callback) callback(result);
			}, 
			error : function(xhr, status, er){
				if(error) error(er);
			}
		})
	},
	
	get : function(rno, callback, error) {
		$.getJSON(`${ctxPath}/freeReplies/${rno}`,function(data){
			if(callback) callback(data);	
		}).fail(function(xhr, status, er){
			if(error) error(er);
		})
	}
}