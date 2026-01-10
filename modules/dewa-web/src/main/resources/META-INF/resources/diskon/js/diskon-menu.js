function tableChecker(width, tableName, length) {
   if (width <= 768) {
      if (length === 0) {
         mobileEmptyTableHandler(tableName);
      } else {
         desktopTableHandler(tableName);
      }
   }
   else {
      desktopTableHandler(tableName);
   }
}

function mobileEmptyTableHandler(tableName) {
   var tableNamePagination = tableName + "_paginate";
   $(tableName).hide();
   $('.empty-table').show();
   $(tableNamePagination).hide();
}

function desktopTableHandler(tableName) {
   var tableNamePagination = tableName + "_paginate";
   $(tableName).show();
   $('.empty-table').hide();
   $(tableNamePagination).show();
}