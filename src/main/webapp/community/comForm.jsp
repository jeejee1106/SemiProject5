<%@page import="data.dao.MemberDAO"%>
<%@page import="data.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//프로젝트의 경로
	String root=request.getContextPath();
	MemberDTO mdto = new MemberDTO();
	MemberDAO mdao = new MemberDAO();
	String id = (String)session.getAttribute("sessionId");
	String nickname = mdao.getIdNick(id);
	String sessionLogin = (String)session.getAttribute("sessionLogin");
%>
<!-- se2 폴더에서 js 파일 가져오기 -->
<script type="text/javascript" src="<%=root%>/community/se2/js/HuskyEZCreator.js"
	charset="utf-8"></script>

<script type="text/javascript" src="<%=root%>/community/se2/photo_uploader/plugin/hp_SE2M_AttachQuickPhoto.js"
	charset="utf-8"></script>	
<body>
<p class="title">커뮤니티</p>
<form action="community/comAction.jsp" method="post">
	<table class="table table-bordered" style="width: 800px;margin-left: 100px;">
		<caption><h3>커뮤니티 글작성</h3></caption>
		<tr>
			<th bgcolor="orange" width="100">작성자</th>
			<td>
				<input type="hidden" name="nickname" class="form-control" value="<%=nickname%>"
					required="required" style="width: 130px;"><%=nickname%>
			</td>
		</tr>
		<tr>
			<th bgcolor="orange" width="100">제  목</th>
			<td>
				<input type="text" name="subject" class="form-control"
					required="required" style="width: 350px;">
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<textarea name="content" id="content"		
					required="required"			
					style="width: 100%;height: 300px;display: none;"></textarea>		
			
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button type="button" class="btn btn-add"
					style="width: 120px;"
					onclick="submitContents(this)">글저장</button>
				
				<button type="button" class="btn btn-add"
					style="width: 120px;"
					onclick="location.href='index.jsp?go=community/communityList.jsp&menu_one=11&menu_two=27'">목록</button>
			</td>
		</tr>
		
	</table>   
</form>

<!-- 스마트게시판에 대한 스크립트 코드 넣기 -->
<script type="text/javascript">
var oEditors = [];

nhn.husky.EZCreator.createInIFrame({

    oAppRef: oEditors,

    elPlaceHolder: "content",

    sSkinURI: "<%=request.getContextPath()%>/se2/SmartEditor2Skin.html",

    fCreator: "createSEditor2"

}); 

//‘저장’ 버튼을 누르는 등 저장을 위한 액션을 했을 때 submitContents가 호출된다고 가정한다.

function submitContents(elClickedObj) {

    // 에디터의 내용이 textarea에 적용된다.

    oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", [ ]);

 

    // 에디터의 내용에 대한 값 검증은 이곳에서

    // document.getElementById("textAreaContent").value를 이용해서 처리한다.
    try {
        elClickedObj.form.submit();
    } catch(e) { 

    }

}

// textArea에 이미지 첨부

function pasteHTML(filepath){
    var sHTML = '<img src="<%=request.getContextPath()%>/save/'+filepath+'">';
    oEditors.getById["content"].exec("PASTE_HTML", [sHTML]); 

}
</script>

</body>
</html>