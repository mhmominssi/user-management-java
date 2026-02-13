import { ApplicationConfig, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideAnimations } from '@angular/platform-browser/animations';

import { provideRouter } from '@angular/router';
import { KENDO_GRID } from '@progress/kendo-angular-grid';

import { routes } from './app.routes';

export const appConfig: ApplicationConfig = {
  providers: [provideAnimations(),
    provideBrowserGlobalErrorListeners(),
    provideRouter(routes)
  ]
};
