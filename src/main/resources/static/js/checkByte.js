
(function (window, $, undefined) {
    var $character_counter_content = $("#character_counter_content"),
        $st_len = $("#current_msglen"),
        $st_byte = $("#current_msg_byte"),
        $steb_len = $("#current_msglen_except_blank"),
        $steb_byte = $("#current_msg_byte_except_blank");
    //실시간 글자수 세기
    $character_counter_content.keyup(function () {
        chkMsgLength(this);
    });

    //텍스트 길이 계산
    function chkMsgLength(objMsg) {
        //공백 포함
        var vacuum_pattern = /\r\n/gm;
        var vacuum_text;
        var vacuum_length;

        //공백 미포함
        var vacuum_remove_pattern = /\s/gm;
        var vacuum_remove_text;
        var vacuum_remove_length;

        vacuum_text = $(objMsg).val();
        vacuum_length = lengthMsg($(objMsg).val());
        vacuum_remove_length = lengthMsg(
            vacuum_text.replace(vacuum_remove_pattern, "")
        );

        vacuum_text = vacuum_text.replace(vacuum_pattern, "\n");
        vacuum_remove_text = vacuum_text.replace(vacuum_remove_pattern, "");

        $st_len.html(vacuum_text.length); //현재 글자수를 넣는다
        $st_byte.html(vacuum_length); //현재 byte수를 넣는다
        $steb_len.html(vacuum_remove_text.length); //현재 글자수를 넣는다
        $steb_byte.html(vacuum_remove_length); //현재 byte수를 넣는다
    }

    //텍스트 바이트단위 계산
    function lengthMsg(obj_msg) {
        var nbytes = 0;
        var i;
        for (i = 0; i < obj_msg.length; i++) {
            var ch = obj_msg.charAt(i);
            if (encodeURIComponent(ch).length > 4) {
                // 한글일경우
                nbytes += 2;
            } else if (ch === "\n") {
                // 줄바꿈일경우
                if (obj_msg.charAt(i - 1) !== "\r") {
                    // 하지만 밀려서 줄이 바뀐경우가 아닐때
                    nbytes += 1;
                }
            } else {
                //나머지는 모두 1byte
                nbytes += 1;
            }
        } //END FOR
        return nbytes;
    }
})(window, jQuery, undefined);