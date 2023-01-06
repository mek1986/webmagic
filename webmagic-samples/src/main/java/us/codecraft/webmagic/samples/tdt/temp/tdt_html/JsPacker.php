<?php
/**
 * Created by Administrator.
 * User: 向往那片天空
 * Date: 2023-1-6
 * Time: 8:33
 * 格言: 抓住中心，宁精勿杂，宁专勿多
 * QQ/微信: 250023777
 * 描述: 无
 */

class JsPacker
{
    static function pack($str)
    {
        if (empty($str)) {
            return $str;
        }

        $str = preg_replace('/\\/\\/[^\\n\\r]*[\\n\\r]/', ' ', $str);
        $str = preg_replace('/\\/\\*[^*]*\\*+([^\\/][^*]*\\*+)*\\//', ' ', $str);
        $str = preg_replace('/(\\b|\\x24)\\s+(\\b|\\x24)/', '$2 $3', $str);
        $str = preg_replace('/([+\\-])\\s+([+\\-])/', '$2 $3', $str);
        $str = preg_replace('/([^\\S]+)(\r\n)\s*?([ \t]*)/', '$2$3$4', $str);

        return $str;
    }
}