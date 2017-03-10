require([
    "common",
], function() {
   var common = require("common");
   
   var currentStore = {};
   
   var handler = function(section, jqElement) {
      if (section === ".admin-add") {
         $("#add-store_name").val("");
         $("#add-store_img").val("");
         $(".btn-admin-file").text("파일선택");
         currentStore = {};
         $("#btn-txt-category").text("카테고리 선택");
         $("#btn-txt-location").text("지역 선택");
         
         $.ajax({
            url: "/admin/api/category/list",
            success: function(list) {
               var itemHTML = "";
               
               for (var i=0; i<list.length; i++) {
                  var item = list[i];
                  
                  itemHTML += "<li><a href='#' item-id='";
                  itemHTML += item.category_id + "'>";                  
                  itemHTML += item.category_name;
                  itemHTML += "</a></li>"
               }
               
               $("#add-category").html(itemHTML);
               
               $("#add-category a").on("click", function(event) {
                  // preventDefault() => 태그가 기본으로 가지고 있는 이벤트 속성을 무시
                  // stop~~ => 하위 태그로의 이벤트의 전파를 방지
                  // <a> 태그의 페이지 이동 이벤트를 막아주기 위해 사용
                  event.preventDefault();
                  
                  var categoryName = $(this).text();
                  $("#btn-txt-category").text(categoryName);
                  
                  var categoryId = $(this).attr("item-id");
                  currentStore.categoryId = categoryId;
               });
            },
         });
         
         $.ajax({
            url: "/admin/api/location/list",
            success: function(list) {
               var itemHTML = "";
               
               for (var i=0; i<list.length; i++) {
                  var item = list[i];
                  
                  itemHTML += "<li><a href='#' item-id='";
                  itemHTML += item.location_id + "'>";
                  itemHTML += item.location_name;
                  itemHTML += "</a></li>"
               }
               
               $("#add-location").html(itemHTML);
               
               $("#add-location a").on("click", function(event) {
               
                  event.preventDefault();
                  
                  var locationName = $(this).text();
                  $("#btn-txt-location").text(locationName);
                  
                  var locationId = $(this).attr("item-id");
                  currentStore.locationId = locationId;
               });
            },
         });   
      }
   };
   
   $(".btn-admin-save").on("click", function() {
      var storeName = $("#add-store_name").val().trim();
      var storeImg = $("#add-store_img").val();
      
      if (storeName === "") {
         alert("맛집명을 입력하세요.");
         $("#add-store_name").focus();
         return;
      }
      else if (storeImg === "") {
         alert("대표이미지를 선택하세요.");
         return;
      }
      else if (!currentStore.categoryId) {
         alert("카테고리를 선택하세요.");
         return;
      }
      else if (!currentStore.locationId) {
         alert("지역을 선택하세요.");
         return;
      }
     /* 폼을 가상으로 만들어주는거 데이터를 채워서 보내는것 */
      var formData = new FormData();
      formData.append("storeName", storeName);
      formData.append("categoryId", currentStore.categoryId);
      formData.append("locationId", currentStore.locationId);
      
      var files = $("#add-store_img")[0].files;
      
      formData.append("storeImg", files[0]);
      /* 파일 보내기*/
      $.ajax({
    	  url: "/admin/api/store/add",
    	  method: "POST",
    	  data: formData,
    	 /* 데이터처리를 내가 하겠다 넌 하지마*/
    	  processData: false,
    	  contentType: false,
    	  success: function() {
				common.showSection(".admin-list", null, handler);
			},
		  error: function() {
				alert("저장에 실패했습니다.");
			},
      });
      
   });
   
   common.initMgmt(handler);
   
});