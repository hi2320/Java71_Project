$("select").click(function(e) {
	 e.stopPropagation();
});

function close_accordion_section() {
	jQuery('#accordian h3').removeClass('active');
jQuery('#accordian .answer_list').slideUp(300).removeClass('open');
	slideExe();
}

var slideExe = function() {
	jQuery('h3').click(function(e) {
// Grab current anchor value
if($(this).is('.active')) {
	/* 자기 자신 다시 클릭시 아무것도 하지 않음 */
} else {
	$('h3').unbind('click');/* 완전 중요 동적으로 이벤트 연결시 중복되는 것 방지 */
	if(jQuery(e.target).is('.active')) {
		close_accordion_section();
	}else {
		close_accordion_section();

		// Add active class to section title
		jQuery(this).addClass('active');
		// Open up the hidden content panel
		jQuery(this).next().slideDown(300).addClass('open'); 
		}
		e.preventDefault();
		}
	});
}

/* question box add ready */
var questionBox = $('.question_list:first').parent().clone();    

function questionDelete(id) {
   event.stopPropagation();
   var questionBoxs = $('.question');
	if(questionBoxs.length != 1) {
     if($(id).parent().parent().index() == ((questionBoxs.length - 1) * 2) 
    		 ||$(id).parent().parent().next().hasClass("question_list")) {/* 버튼 > question > question_list > next */
      $(id).parent().parent().parent().remove();
      $('.question_list:last').find('h3').trigger('click');
      } else {
  	   $(id).parent().parent().parent().remove();
      }
	} else {
		alert('마지막 질문란입니다');
	}
}/* question Delete end */

function questionAdd() {
    var questionBoxClone = $(questionBox[0]).clone();
    questionBoxClone.find("input").val("");
    answersExist = questionBoxClone.find(".answer");
    if(answersExist.length != 1) { /* 복제한 곳에 답변이 여러개면 한개로 만들기 */
    	var answer = answersExist[0];
    	$(questionBoxClone).find(".answer").remove();
    	$(questionBoxClone).find(".answer_list").append(answer);
    }
    $("#accordian").append(questionBoxClone[0]);
    close_accordion_section();
    questionBoxClone.find('h3').trigger('click');
}/* question Add end */

function answerDelete(id) {
	var isLast = $(id).parent().next().hasClass('answer')
			|| $(id).parent().prev().hasClass('answer');
	if (isLast) {
		if ($(id).next().hasClass('answeraddbtn')) {
			$(id).parent().prev().append($(id).next());
		}
		$(id).parent().remove();
	} else {
		alert('마지막 답변란입니다');
	}
}
function answerAdd(id) {
	var thisForm = $(id).parent();
	var formClone = thisForm.clone();
	formClone.find('input').val('');
	formClone.appendTo(thisForm.parent());
	$(id).remove();
}
function curatorPageChange(index) {
	$('[name=curatingForm]').attr('action', '/app/curator/changeQuestionPage?curId='+index);
	$('[name=curatingForm]').submit();
}

function sendQuestionManage() {
    var questions = $(".question_list").parent();

    $('.curating_code').attr('name', 'curId');
    $('[name=purpose]').val($('.curating_code option:selected').text());
    for(var i = 0; i < questions.length; i++) {
       var question = questions[i];
       
       $(question).find('[class*=question_texts]').attr('name', 'questionList['+i+'].qSente');
       $(question).find('[class*=product_value]').attr('name', 'questionList['+i+'].qProd');
       $(question).find('[class*=answer_value]').attr('name', 'questionList['+i+'].qType');
        var answers = $(question).find('.answer');
        for(var j = 0; j < answers.length; j++) {
            var answer = answers[j];
            $(answer).find('[class*=answer_text]').attr('name', 'questionList['+i+'].answerList['+j+'].aSente');
            $(answer).find('[class*=answer_hidden]').attr('name', 'questionList['+i+'].answerList['+j+'].aSpec');
        }
    }
    $('[name=curatingForm]').submit();
}
$(document).ready(function() {
	close_accordion_section();
});