<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<br />
				<div style="float: left; vertical-align: bottom; padding-top: 4px;">나상선</div>
				<div>
					<input class="normal-form" type="text" value="test"
						style="width: 650px;" /><br />
				</div>
				<input class="normal-form" type="text" value="test"
					style="width: 700px;" />
				<textarea class="normal-form" style='min-height: 450px; width: 700px;'></textarea>
				<input class="writecomplete" type="submit" value="글쓰기" style="width: 60px; height: 25px;"/><br />
				<input type=”checkbox” name=”mailing-list” id=”mailing-list” value=”no” /><br />
				<input type="checkbox" name="checkbox1" value="checkbox_value" checked><br />
				<input type="text" value="없음" class="normal-form">
			<form>
  <label for="favoritefood">Favorite food</label>
  <select name="favoritefood" id="favoritefood">
    <option>Cheese</option>
    <option>Egg</option>
    <option>Cabbage</option>
    <option>Cabbage</option>
    <option>Cabbage</option>
    <option>Cabbage</option>
    <option>Cabbage</option>
    <option>Cabbage</option>
  </select>
</form>