$(document).ready(function() {
  $(".search1").keyup(function () {
    var searchTerm = $(".search1").val();
    var listItem = $('.results1 tbody').children('tr');
    var searchSplit = searchTerm.replace(/ /g, "'):containsi('")
    
  $.extend($.expr[':'], {'containsi': function(elem, i, match, array){
        return (elem.textContent || elem.innerText || '').toLowerCase().indexOf((match[3] || "").toLowerCase()) >= 0;
    }
  });
    
  $(".results1 tbody tr").not(":containsi('" + searchSplit + "')").each(function(e){
    $(this).attr('visible','false');
  });

  $(".results1 tbody tr:containsi('" + searchSplit + "')").each(function(e){
    $(this).attr('visible','true');
  });

  var jobCount = $('.results1 tbody tr[visible="true"]').length;
    $('.counter1').text(jobCount + ' item');

  if(jobCount == '0') {$('.no-result1').show();}
    else {$('.no-result1').hide();}
      });
});



$(document).ready(function() {
  $(".search2").keyup(function () {
    var searchTerm = $(".search2").val();
    var listItem = $('.results2 tbody').children('tr');
    var searchSplit = searchTerm.replace(/ /g, "'):containsi('")
    
  $.extend($.expr[':'], {'containsi': function(elem, i, match, array){
        return (elem.textContent || elem.innerText || '').toLowerCase().indexOf((match[3] || "").toLowerCase()) >= 0;
    }
  });
    
  $(".results2 tbody tr").not(":containsi('" + searchSplit + "')").each(function(e){
    $(this).attr('visible','false');
  });

  $(".results2 tbody tr:containsi('" + searchSplit + "')").each(function(e){
    $(this).attr('visible','true');
  });

  var jobCount = $('.results2 tbody tr[visible="true"]').length;
    $('.counter2').text(jobCount + ' item');

  if(jobCount == '0') {$('.no-result2').show();}
    else {$('.no-result2').hide();}
      });
});




$(document).ready(function() {
  $(".search3").keyup(function () {
    var searchTerm = $(".search3").val();
    var listItem = $('.results3 tbody').children('tr');
    var searchSplit = searchTerm.replace(/ /g, "'):containsi('")
    
  $.extend($.expr[':'], {'containsi': function(elem, i, match, array){
        return (elem.textContent || elem.innerText || '').toLowerCase().indexOf((match[3] || "").toLowerCase()) >= 0;
    }
  });
    
  $(".results3 tbody tr").not(":containsi('" + searchSplit + "')").each(function(e){
    $(this).attr('visible','false');
  });

  $(".results3 tbody tr:containsi('" + searchSplit + "')").each(function(e){
    $(this).attr('visible','true');
  });

  var jobCount = $('.results3 tbody tr[visible="true"]').length;
    $('.counter3').text(jobCount + ' item');

  if(jobCount == '0') {$('.no-result3').show();}
    else {$('.no-result3').hide();}
      });
});