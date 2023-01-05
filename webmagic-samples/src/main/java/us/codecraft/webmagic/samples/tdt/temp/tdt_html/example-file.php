<?php
// you can pass this script to PHP CLI to convert your file.

// adapt these 2 paths to your files.
require 'class.JavaScriptPacker.php';

$src = 'd:\temp\js\core.js';
$out = 'd:\temp\js\core.min.js';
$encode = $_GET['code'];

$encodes = JavaScriptPacker::getEncoding();

if (empty($encode) || !isset($encodes[$encode])) {
    $encode = "None";
}

// or uncomment these lines to use the argc and argv passed by CLI :
/*
if ($argc >= 3) {
	$src = $argv[1];
	$out = $argv[2];
} else {
	echo 'you must specify  a source file and a result filename',"\n";
	echo 'example :', "\n", 'php example-file.php myScript-src.js myPackedScript.js',"\n";
	return;
}
*/

$script = file_get_contents($src);

$t1 = microtime(true);

$packer = new JavaScriptPacker($script, $encode, true, false);
$packed = $packer->pack();

$t2 = microtime(true);
$time = sprintf('%.4f', ($t2 - $t1));
echo 'script ', $src, ' packed in ', $out, ', in ', $time, ' s.', "\n";

file_put_contents($out, $packed);
?>
