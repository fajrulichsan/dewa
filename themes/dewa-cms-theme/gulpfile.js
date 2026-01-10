/**
 * © 2017 Liferay, Inc. <https://liferay.com>
 *
 * SPDX-License-Identifier: MIT
 */

'use strict';

var gulp = require('gulp');
var liferayThemeTasks = require('liferay-theme-tasks');

liferayThemeTasks.registerTasks({
	gulp,
	hookFn: function(gulp) {
		// Copy Font Awesome webfonts to build folder
		gulp.hook('after:build', function(done) {
			gulp.src('src/css/font-awesome/webfonts/**/*')
				.pipe(gulp.dest('build/webfonts'))
				.on('end', done);
		});
	}
});
