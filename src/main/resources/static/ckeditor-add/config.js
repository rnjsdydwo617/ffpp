/**
 * @license Copyright (c) 2003-2019, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function( config ) {
	config.toolbarGroups = [
		{ name: 'clipboard',   groups: [ 'clipboard', 'undo' ] },
		{ name: 'editing',     groups: [ 'find', 'selection', 'spellchecker' ] },
		{ name: 'links' },
		{ name: 'insert' },
		{ name: 'forms' },
		{ name: 'tools' },
		{ name: 'document',	   groups: [ 'mode', 'document', 'doctools' ] },
		{ name: 'others' },
		'/',
		{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
		{ name: 'paragraph',   groups: [ 'list', 'indent', 'blocks', 'align', 'bidi','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock' ] },
		{ name: 'styles' },
		{ name: 'colors' },
		{ name: 'about' }
	];

	config.removeButtons = 'Underline,Subscript,Superscript';
	config.format_tags = 'p;h1;h2;h3;pre';
	config.filebrowserUploadMethod = 'form';
	config.removeDialogTabs = 'image:advanced;link:advanced';
};
