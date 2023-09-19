$(function(){

	let bnoValue = $('[name="bno"]').val()
	let uploadResultList = [] // 새로 추가할 파일 목록
	let toBeDelList = [] // 삭제할 파일 목록

	let showUploadResult = function(attachList){
		let fileList = ''
		$(attachList).each(function(i,e){
			uploadResultList.push(e); // 첨부파일 배열의 요소로 추가
			fileList += 
			`<li class="list-group-item" data-uuid="${e.uuid}">
				<div class="float-left">`
				if(e.fileType){ // 이미지 파일일 때
					let filePath = e.uploadPath+"/s_"+e.uuid+"_"+e.fileName
					let encodingFilePath = encodeURIComponent(filePath)
					fileList +=
					`<div class="d-inline-block mr-4">
						<img alt="첨부파일" src="${ctxPath}/free/files/display?fileName=${encodingFilePath}">
					</div>`
				} else { // 이미지 파일이 아닐 때
					fileList += 		
					`<div class="d-inline-block mr-4" style="width: 40px">
						<img alt="첨부파일" src="${ctxPath}/resources/images/attach.png" style="width: 100%">
					</div>`
				}
					fileList +=	
					`<div class="d-inline-block">
						${e.fileName}
					</div>
				</div>
				<div class="float-right">
					<a href="#" class="delete">삭제</a>
				</div>
			</li>`		
		})
		$('.uploadResultDiv ul').append(fileList)
	}

	// 첨부파일 목록 불러오기
	$.getJSON(
	`${ctxPath}/free/getAttachList`,
	{bno : bnoValue},
	function(attachList){
			console.log(attachList)

			let fileList = ''
			$(attachList).each(function(i,e){
				fileList += 
				`<li class="list-group-item" data-uuid="${e.uuid}">
					<div class="float-left">`
					if(e.fileType){ // 이미지 파일일 때
						let filePath = e.uploadPath+"/s_"+e.uuid+"_"+e.fileName
						let encodingFilePath = encodeURIComponent(filePath)
						fileList +=
						`<div class="d-inline-block mr-4">
							<img alt="첨부파일" src="${ctxPath}/free/files/display?fileName=${encodingFilePath}">
						</div>`
					} else { // 이미지 파일이 아닐 때
						fileList += 		
						`<div class="d-inline-block mr-4" style="width: 40px">
							<img alt="첨부파일" src="${ctxPath}/resources/images/attach.png" style="width: 100%">
						</div>`
					}
						fileList +=	
						`<div class="d-inline-block">
							${e.fileName}
						</div>
					</div>
					<div class="float-right">`
					if(e.fileType){
						let imgUrl = encodeURIComponent(e.uploadPath+"/"+e.uuid+"_"+e.fileName)
						fileList +=	`<a href="${imgUrl}" class="showImage">원본</a>`
					} else {
						let fileName = encodeURIComponent(e.uploadPath+"/"+e.uuid+"_"+e.fileName)
						fileList +=	`<a href="${ctxPath}/free/files/download?fileName=${fileName}">다운로드</a>`
					}
					fileList +=	`
						<div class="form-check-inline ml-2">
							<label class="form-check">
								<input type="checkbox" class="form-check-input toBeDelFile">
								<span>삭제</span>
							</label>
						</div>
					</div></li>`		
			})
			$('.uploadResultDiv ul').html(fileList)
		}) // getJSON end

		// 원본이미지 보기
		$('.uploadResultDiv ul').on('click','.showImage',function(e){
			e.preventDefault()
			let filePath = $(this).attr('href')
			let imgSrc = `${ctxPath}/free/files/display?fileName=${filePath}`
			let imgTag = $('<img>' , {src : imgSrc, class : 'img-fluid'} )
			$('#showImage').find('.modal-body').html(imgTag)
			$('#showImage').modal()
		})

	// 파일 추가 업로드
	$('[type="file"]').change(function(){
		let formData = new FormData(); 
		let files = this.files;
		
		// checkExtension() 파일 유효성 검사
		for(let f of files){
			if(!checkExtension(f.name, f.size)){
				$(this).val('')
				return
			}
			formData.append('uploadFile',f);
		}
		
		
		$.ajax({
			url : `${ctxPath}/free/files/upload`, 
			type : 'post', 
			data : formData, 
			processData : false, 
			contentType : false, 
			dataType : 'json', 
			success : function(attachList){
				showUploadResult(attachList)
				console.log(uploadResultList)
			}
		});
			
	});
	
	// 새로 첨부한 파일 삭제 : register와 동일
	$('.uploadResultDiv ul').on('click','.delete',function(e){
		e.preventDefault()
		let fileLi = $(this).closest('li')
		let uuid = fileLi.data('uuid')
		let targetFile = null
		let targetFileIdx = -1
		
		$(uploadResultList).each(function(i,e){
			if(e.uuid == uuid) {
				targetFileIdx = i
				targetFile = e
				return
			}
		})
		
		if(targetFileIdx!=-1) uploadResultList.splice(targetFileIdx,1)
		
		console.log(targetFile)
		console.log(uploadResultList)
		
		$.ajax({
			type : 'post',
			url : `${ctxPath}/free/files/deleteFile`,
			data : targetFile,
			success : function(result){
						console.log(result)
			}
		})
		
		fileLi.remove()
	})

	// 기존에 업로드 된 파일 삭제
	// 삭제할 파일목록 체크
	// 체크된 파일 정보를 api를 호출하여 가져옴
	// 반환된 BoardAttachVO 객체를 삭제할 파일 목록 배열에 추가
	// -------------------------------------------------
	// 삭제할 파일목록 해제
	// 삭제할 파일 목록 배열에 삭제
	$('.uploadResultDiv ul').on('change','.toBeDelFile',function(e){	
		let listTag = $(this).closest('li')
		let uuidVal = listTag.data('uuid')
		
		if($(this).is(':checked')) { // 체크
			$.ajax({
				type : 'get',
				url : `${ctxPath}/free/getAttachFileInfo`,
				data : {uuid : uuidVal},
				success : function(freeAttachVO){
					toBeDelList.push(freeAttachVO)
				}
			}) // ajax end

		
		} else { // 체크 해제
			toBeDelList = toBeDelList.filter(e=> e.uuid != uuidVal)
		}
		console.log(toBeDelList)
	})

		// 목록, 삭제, 수정 처리
		$('.modifyForm button').click(function(){
		let operation =$(this).data('oper')
		addCriteria();
		if(operation=='remove'){
			formObj.attr('action',`${ctxPath}/free/remove`);
			
		} else if (operation=='list'){
			formObj.empty();
			addCriteria();
			formObj.attr('action',`${ctxPath}/free/list`)
				   .attr('method','get');
		} else { //수정 처리
			// List<BoardAttachVO>
			// 삭제 대상 첨부파일 목록이 있을 때
			let idx=0
			if(toBeDelList.length>0){
				console.log(toBeDelList)
				$(toBeDelList).each(function(i,e){ // i=0,1,2
					let bno = $('<input/>',{type:'hidden', name:`attachList[${i}].bno`, value : `${e.bno}`})
					let uuid = $('<input/>',{type:'hidden', name:`attachList[${i}].uuid`, value : `${e.uuid}`})
					let fileName = $('<input/>',{type:'hidden', name:`attachList[${i}].fileName`, value : `${e.fileName}`})
					let fileType = $('<input/>',{type:'hidden', name:`attachList[${i}].fileType`, value : `${e.fileType}`})
					let uploadPath = $('<input/>',{type:'hidden', name:`attachList[${i}].uploadPath`, value : `${e.uploadPath}`})
					formObj.append(uuid)
						.append(bno)
						.append(fileName)
						.append(fileType)
						.append(uploadPath)
					idx = ++i
				})
			}
			
			// 새로 추가하는 파일
			if(uploadResultList.length>0){
				console.log(uploadResultList)
				$(uploadResultList).each(function(i,e){
					let uuid = $('<input/>',{type:'hidden', name:`attachList[${idx+i}].uuid`, value : `${e.uuid}`})
					let fileName = $('<input/>',{type:'hidden', name:`attachList[${idx+i}].fileName`, value : `${e.fileName}`})
					let fileType = $('<input/>',{type:'hidden', name:`attachList[${idx+i}].fileType`, value : `${e.fileType}`})
					let uploadPath = $('<input/>',{type:'hidden', name:`attachList[${idx+i}].uploadPath`, value : `${e.uploadPath}`})
					formObj.append(uuid)
						.append(fileName)
						.append(fileType)
						.append(uploadPath)
				})
			}
		} 	
		
		formObj.submit();
		
		
		});
	
	// 카테고리 드롭다운시 값 변경
	$('#category').change(function(){
		let cno = $(this).val()
		formObj.find('[name="cno"]').val(cno)
	})
})