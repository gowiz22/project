var replyService = {

	add : function(reply, callback, error) {
		$.ajax({
			type : 'post',
			url : `${ctxPath}/productReplies/new`,
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
		let pno = param.pno
		let page = param.page || 1;
		
		$.ajax({
			type : 'get', 
			url : `${ctxPath}/productReplies/pages/${page}/${pno}`, 
			success : function(reviewPageDTO){
				if(callback) callback(reviewPageDTO.replyCount, reviewPageDTO.list);
			}, 
			error : function(xhr, status, er){
				if(error) error(er);
			}
		})
	},

	getReviewerList : function(param, callback, error) {
		let pno = param.pno
		
		$.ajax({
			type : 'post',
			url : `${ctxPath}/productReplies/pages/${pno}`,
			success : function(reviewList){
				if(callback) callback(reviewList);
			}, 
			error : function(xhr, status, er){
				if(error) error(er);
			}
		})
	},

	
	update : function(reply, callback, error) {
		$.ajax({
			type : 'put', 
			url : `${ctxPath}/productReplies/${reply.rno}`, 
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
			url : `${ctxPath}/productReplies/${rno}`, 
			success : function(result){
				if(callback) callback(result);
			}, 
			error : function(xhr, status, er){
				if(error) error(er);
			}
		})
	},
	
	get : function(rno, callback, error) {
		$.getJSON(`${ctxPath}/productReplies/${rno}`,function(data){
			if(callback) callback(data);	
		}).fail(function(xhr, status, er){
			if(error) error(er);
		})
	}
}